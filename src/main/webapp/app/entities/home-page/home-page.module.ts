import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomePageComponent} from "./home-page.component";
import {homePageRoute} from "./home-page.route";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(homePageRoute)],
    declarations: [HomePageComponent],
    exports: [RouterModule]
})
export class HomePageModule {}
