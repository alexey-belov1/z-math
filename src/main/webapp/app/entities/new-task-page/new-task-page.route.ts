import {Routes} from "@angular/router";
import {NewTaskPageComponent} from "./new-task-page.component";
import {AuthGuard} from "../../shared/guards/auth.guard";

export const newTaskPageRoute: Routes = [
    {path: '', component: NewTaskPageComponent, canActivate: [AuthGuard]}
];
