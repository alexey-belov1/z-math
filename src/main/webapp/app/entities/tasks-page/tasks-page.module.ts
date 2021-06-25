import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {tasksPageRoute} from "./tasks-page.route";
import {TasksPageComponent} from "./tasks-page.component";
import {TaskDetailComponent} from "./task-detail/task-detail.component";
import {TaskEditCostComponent} from "./task-edit-cost/task-edit-cost.component";
import {TaskEditRefuseComponent} from "./task-edit-refuse/task-edit-refuse.component";
import {TaskEditSolveComponent} from "./task-edit-solve/task-edit-solve.component";
import {TaskEditPaymentComponent} from "./task-edit-payment/task-edit-payment.component";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(tasksPageRoute)],
    declarations: [
        TasksPageComponent,
        TaskDetailComponent,
        TaskEditCostComponent,
        TaskEditRefuseComponent,
        TaskEditSolveComponent,
        TaskEditPaymentComponent
    ],
    entryComponents: [
        TaskDetailComponent,
        TaskEditCostComponent,
        TaskEditRefuseComponent,
        TaskEditSolveComponent,
        TaskEditPaymentComponent
    ],
    exports: [RouterModule]
})
export class TasksPageModule {}
