import {AlertType} from "./enum/alert-type.model";

export interface IAlert {
    type: AlertType;
    message: string;
    autoClose?: boolean;
}
