import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {RegistrationPageComponent} from "./registration-page.component";
import {registrationPageRoute} from "./registration-page.route";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(registrationPageRoute)],
    declarations: [RegistrationPageComponent],
    exports: [RouterModule]
})
export class RegistrationPageModule {}
