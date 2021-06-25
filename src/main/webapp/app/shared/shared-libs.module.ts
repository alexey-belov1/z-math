import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {CommonModule} from "@angular/common";

@NgModule({
    exports: [
        FormsModule,
        ReactiveFormsModule,
        NgbModule,
        CommonModule
    ]
})
export class SharedLibsModule {}
