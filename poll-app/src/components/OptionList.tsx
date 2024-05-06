import { useEffect, useState } from "react";
import { get, put } from "./fetchAPI";
import { Poll, PollData } from "./types";

type Props = {
    pollId: number;
    maxSelections: number;
}

const OptionList: React.FC<Props> = ({ pollId, maxSelections }) => {

    const [voted, setVoted] = useState<Map<number, boolean>>();

    const [data, setData] = useState<PollData[]>([]);

    useEffect(() => {
        get(`polls/${pollId}`).then(reponse => reponse.json())
            .then((poll: Poll) => {
                setData(poll.pollData);
            })
    }, [pollId]);

    useEffect(() => {
        const map = new Map(data.map(d => ([d.id, false])));
        setVoted(map);

    }, [data])

    const getBoxesTicked = (m: Map<number, boolean>) => {
        let selected = 0;
        m?.forEach((value) => value ? ++selected : 0);

        return selected;
    }

    const isSubmitDisabled = () => {
        return voted && getBoxesTicked(voted) === 0;
    }

    const changeVote = (event: React.ChangeEvent<HTMLInputElement>, data: PollData) => {
        const map = new Map(voted);
        map.set(data.id, event.target.checked);

        const selected = getBoxesTicked(map);

        if (selected > maxSelections) {
            console.log("too many boxes ticked", selected);
        } else {
            setVoted(map);
        }
    }

    const isVoted = (id: number) => {
        const value = voted?.get(id);
        return value || false;
    }

    const submitVotes = () => {
        const idList: number[] = [];
        voted?.forEach((value, key) => {
            if (value) {
                idList.push(key);
                console.log('push id', idList);
            }
        });

        put(`polls/${pollId}`, { idList })
            .then(response => {
                console.log('submit votes for poll ' + pollId, idList);
                const newVoted = new Map();
                voted?.forEach((_, id) => newVoted.set(id, false));
                setVoted(newVoted);
                return response.json();
            })
            .then((poll: Poll) => {
                setData(poll.pollData);
            });
    }

    return <div>
        {data ?
            data.map(d => <div key={d.id}>
                <div>
                    <span style={{ marginRight: '10px' }}>
                        <input type={'checkbox'} checked={isVoted(d.id)} onChange={(e) => changeVote(e, d)} />
                    </span>
                    <span>{d.option} ({d.votes})</span>
                </div>
            </div>)
            : <span />}
        <button type='button' onClick={submitVotes} disabled={isSubmitDisabled()}
            style={{ marginTop: '20px', fontWeight: 'bold' }} >
            Submit
        </button>
    </div>
}

export default OptionList;