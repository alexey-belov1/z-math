import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {rulesPageRoute} from "./rules-page.route";
import {RulesPageComponent} from "./rules-page.component";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(rulesPageRoute)],
    declarations: [RulesPageComponent],
    exports: [RouterModule]
})
export class RulesPageModule {}
