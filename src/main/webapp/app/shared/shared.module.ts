import { NgModule } from '@angular/core';
import {HasAnyRoleDirective} from "./directives/has-any-role.directive";
import {TooltipDirective} from "./directives/tooltip.directive";
import {SharedLibsModule} from "./shared-libs.module";

@NgModule({
    imports: [SharedLibsModule],
    declarations: [
        HasAnyRoleDirective,
        TooltipDirective
    ],
    exports: [
        HasAnyRoleDirective,
        TooltipDirective,
        SharedLibsModule
    ]
})
export class SharedModule {}
