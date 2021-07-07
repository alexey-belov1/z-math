import {IRole} from "./role.model";

export interface IUser {
    id?: number;
    login?: string;
    email?: string;
    password?: string;
    role?: IRole;
    created?: Date;
}
