import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {noAuthorizationPageRoute} from "./no-authorization-page.route";
import {NoAuthorizationPageComponent} from "./no-authorization-page.component";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(noAuthorizationPageRoute)],
    declarations: [NoAuthorizationPageComponent],
    exports: [RouterModule]
})
export class NoAuthorizationPageModule {}
