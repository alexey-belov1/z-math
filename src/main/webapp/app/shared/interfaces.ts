export interface IUser {
    id?: number;
    login?: string;
    balance?: number;
    created?: Date;
    email?: string;
    password?: string;
    role?: IRole;
}

export interface IAttachedFile {
    id?: number;
    name?: string;
    size?: string;
    extension?: string;
    path?: string;
    taskId?: number;
    file?: File;
    type?: String;
}

export interface AuthResponse {
    Authorization: string;
    Expires: string;
    Login: string;
    Role: string;
}

export interface ISubject {
    id?: number;
    name?: string;
}

export interface ITask {
    id?: number;
    user?: IUser,
    subject?: ISubject,
    files?: string;
    comment?: string;
    deadline?: Date;
    status?: IStatus,
    cost?: number;
    paid?: number;
    method?: IMethod;
    created?: Date;
    contact?: string;
    cause?: string;
    archived?: boolean;
    attachedFile?: IAttachedFile[];
}

export class Task implements ITask {
    constructor(
        public id?: number,
        public user?: IUser,
        public subject?: ISubject,
        public files?: string,
        public comment?: string,
        public deadline?: Date,
        public status?: IStatus,
        public cost?: number,
        public paid?: number,
        public method?: IMethod,
        public created?: Date,
        public contact?: string,
        public cause?: string,
        public archived?: boolean,
        public attachedFile?: IAttachedFile[]
    ) {
    }
}


export interface IReview {
    user: IUser;
    created?: Date;
    text: string;
}

export interface IStatus {
    id: number;
    name?: string;
}

export interface IRole {
    id: number;
    name?: string;
}

export interface IMethod {
    id: number;
    name?: string;
    description?: string;
}

export class Method implements IMethod {
    constructor(
        public id: number,
        public name?: string,
        public description?: string
    ) { }
}

export interface IAlert {
    type: AlertType;
    message: string;
    autoClose?: boolean;
}

export enum AlertType {
    Success,
    Error,
    Info,
    Warning
}

export class EventData {
    constructor(public name: any, public value: any) { }
}
