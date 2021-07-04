import {IMethod} from "./method.model";

export interface IPayment {
    id?: number;
    created?: Date;
    method?: IMethod;
    amount?: number;
}
