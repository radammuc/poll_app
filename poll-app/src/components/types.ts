export type PollData = {
    id: number;
    option: string;
    votes: number;
}

export type Poll = {
    id: number;
    pollData: PollData[];
    title: string;
    description: string;
    maxSelections: number;
}

export type SelectedOptions = {
    idList: number[];
}