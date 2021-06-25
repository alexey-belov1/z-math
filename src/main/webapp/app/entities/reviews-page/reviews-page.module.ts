import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {reviewsPageRoute} from "./reviews-page.route";
import {ReviewsPageComponent} from "./reviews-page.component";
import {NewReviewComponent} from "./new-review/new-review.component";
import {SharedModule} from "../../shared/shared.module";

@NgModule({
    imports: [SharedModule, RouterModule.forChild(reviewsPageRoute)],
    declarations: [
        ReviewsPageComponent,
        NewReviewComponent
    ],
    exports: [RouterModule]
})
export class ReviewsPageModule {}
