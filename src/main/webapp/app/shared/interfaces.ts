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
}

export interface AuthResponse {
    Authorization: string;
    Expires: string;
    Login: string;
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
    created?: Date;
    contact?: string;
    cause?: string;
    hidden?: boolean;
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
        public created?: Date,
        public contact?: string,
        public cause?: string,
        public hidden?: boolean,
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
