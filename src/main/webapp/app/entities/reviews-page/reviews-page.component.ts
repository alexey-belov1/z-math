import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../shared/services/auth.service";
import {ReviewService} from "../../shared/services/review.service";
import {IReview} from "../../shared/model/review.model";

@Component({
    selector: 'app-reviews-page',
    templateUrl: './reviews-page.component.html',
    styleUrls: ['./reviews-page.component.scss']
})
export class ReviewsPageComponent implements OnInit {

    reviews: IReview[] = [];

    constructor(
        private authService: AuthService,
        private reviewService: ReviewService) {
    }

    ngOnInit(): void {
        this.loadReviews();
    }

    loadReviews(): void {
        this.reviewService.findAll().subscribe(res => {
            this.reviews = res.body;
        });
    }

    isAuthenticated(): boolean {
        return this.authService.isAuthenticated();
    }
}
