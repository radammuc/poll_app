import { FC, useEffect, useState } from "react";
import OptionList from "./OptionList";
import { Poll } from "./types";

type Props = {
    polls: Poll[];
}

const PollList: FC<Props> = ({ polls }) => {
    const [selectedPoll, setSelectedPoll] = useState<number>();

    useEffect(() => {
        if (polls && polls.length > 1) {
            setSelectedPoll(polls[0].id);
        }
    }, [polls]);

    const getSelectionsText = (maxSelections: number) => {
        return maxSelections > 1 ?
            `Select up to ${maxSelections} options`
            : 'Select 1 option'
    }

    return <div>
        {polls ? polls.map(p => {
            return <div key={p.id} className='event'>
                <h3 onClick={() => setSelectedPoll(p.id)} className={selectedPoll === p.id ? '' : 'event-not-selected'}>
                    {p.title}
                </h3>
                <div className={selectedPoll === p.id ? '' : 'collapsed'}>
                    <div style={{ marginBottom: '10px', fontStyle: 'italic' }} >
                        {p.description}
                    </div>
                    <div style={{ marginBottom: '10px', fontSize: 'smaller' }} >
                        {getSelectionsText(p.maxSelections)}
                    </div>
                    <OptionList pollId={p.id} maxSelections={p.maxSelections} />
                </div>
            </div>
        }) : <span />}
    </div >
}

export default PollList;