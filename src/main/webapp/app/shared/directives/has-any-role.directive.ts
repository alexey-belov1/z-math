import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Directive({
    selector: '[hasAnyRole]'
})
export class HasAnyRoleDirective {

    private : string[] = [];

    constructor(
        private authService: AuthService,
        private templateRef: TemplateRef<any>,
        private viewContainerRef: ViewContainerRef
    ) {}

    @Input()
    set hasAnyRole(roles: string | string[]) {
        roles = typeof roles === 'string' ? [roles] : roles;
        this.viewContainerRef.clear();
        if (this.authService.hasAnyRole(roles)) {
            this.viewContainerRef.createEmbeddedView(this.templateRef);
        }
    }
}
