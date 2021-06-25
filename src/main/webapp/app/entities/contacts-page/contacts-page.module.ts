import {RouterModule} from "@angular/router";
import {contactsPageRoute} from "./contacts-page.route";
import {ContactsPageComponent} from "./contacts-page.component";
import {NgModule} from "@angular/core";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(contactsPageRoute)],
    declarations: [ContactsPageComponent],
    exports: [RouterModule]
})
export class ContactsPageModule {}
