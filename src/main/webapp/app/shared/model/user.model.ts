import {IRole} from "./role.model";

export interface IUser {
    id?: number;
    login?: string;
    balance?: number;
    created?: Date;
    email?: string;
    password?: string;
    role?: IRole;
}
