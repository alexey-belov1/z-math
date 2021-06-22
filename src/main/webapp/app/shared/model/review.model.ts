import {IUser} from "./user.model";

export interface IReview {
    user: IUser;
    created?: Date;
    text: string;
}
