import {ISubject} from "./subject.model";
import {IStatus} from "./status.model";
import {IAttachedFile} from "./attached-file.model";
import {IPayment} from "./payment.model";

export interface ITask {
    id?: number;
    userId?: number,
    userLogin?: string,
    subject?: ISubject,
    files?: string;
    comment?: string;
    deadline?: Date;
    status?: IStatus,
    cost?: number;
    paid?: number;
    payment?: IPayment;
    created?: Date;
    contact?: string;
    cause?: string;
    archived?: boolean;
    attachedFiles?: IAttachedFile[];
}

export class Task implements ITask {
    constructor(
        public id?: number,
        public userId?: number,
        public userLogin?: string,
        public subject?: ISubject,
        public files?: string,
        public comment?: string,
        public deadline?: Date,
        public status?: IStatus,
        public cost?: number,
        public paid?: number,
        public payment?: IPayment,
        public created?: Date,
        public contact?: string,
        public cause?: string,
        public archived?: boolean,
        public attachedFiles?: IAttachedFile[]
    ) {
    }
}
