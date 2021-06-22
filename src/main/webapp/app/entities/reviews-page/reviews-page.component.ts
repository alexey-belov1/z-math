import { Component, OnInit } from '@angular/core';
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
    private reviewSevice: ReviewService
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.reviewSevice.getAll()
      .subscribe(reviews => {
        this.reviews = reviews;
        console.log(reviews);
      });
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
}
