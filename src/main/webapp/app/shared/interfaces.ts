export interface IUser {
    id?: number;
    login?: string;
    balance?: number;
    created?: Date;
    email?: string;
    password?: string;
    role?: IRole;
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
    user: IUser,
    subject?: ISubject,
    file: string;
    comment: string;
    deadline: Date;
    status?: IStatus,
    cost: number;
    created?: Date;
    contact?: string;
    cause?: string;
    hidden: boolean;
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
