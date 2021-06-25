import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {passwordRecoveryPageRoute} from "./password-recovery-page.route";
import {PasswordRecoveryPageComponent} from "./password-recovery-page.component";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(passwordRecoveryPageRoute)],
    declarations: [PasswordRecoveryPageComponent],
    exports: [RouterModule]
})
export class PasswordRecoveryPageModule {}
