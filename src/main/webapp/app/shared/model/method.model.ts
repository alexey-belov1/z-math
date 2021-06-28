export interface IMethod {
    id?: number;
    name?: string;
    description?: string;
}

export class Method implements IMethod {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string
    ) { }
}
