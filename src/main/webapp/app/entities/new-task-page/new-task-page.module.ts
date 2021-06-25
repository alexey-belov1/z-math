import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {NewTaskPageComponent} from "./new-task-page.component";
import {newTaskPageRoute} from "./new-task-page.route";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(newTaskPageRoute)],
    declarations: [NewTaskPageComponent],
    exports: [RouterModule]
})
export class NewTaskPageModule {}
