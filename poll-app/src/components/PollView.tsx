import { useEffect, useState } from "react"
import { get } from "./fetchAPI";
import PollList from "./PollList";
import { Poll } from "./types";

const PollView = () => {
    const [polls, setPolls] = useState<Poll[]>([]);

    useEffect(() => {
        get('polls')
            .then(response => response.json())
            .then(data => setPolls(data));

    }, [])

    return <div>
        <PollList polls={polls} />
    </div>
}

export default PollView;