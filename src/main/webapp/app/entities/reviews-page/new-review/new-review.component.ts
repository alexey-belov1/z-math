import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ReviewService} from "../../../shared/services/review.service";
import {IReview} from "../../../shared/model/review.model";

@Component({
    selector: 'app-new-review',
    templateUrl: './new-review.component.html',
    styleUrls: ['./new-review.component.scss']
})
export class NewReviewComponent implements OnInit {

    @Output() onSaveReview = new EventEmitter();

    form: FormGroup;

    submitted = false;

    constructor(private reviewService: ReviewService) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            text: new FormControl(null, [Validators.required])
        });
    }

    submit(): void {
        if (this.form.invalid) {
            return;
        }
        const review: IReview = {
            text: this.form.get('text').value
        };
        this.submitted = true;

        this.reviewService.save(review).subscribe(() => {
            this.form.reset();
            this.onSaveReview.emit();
            this.submitted = false;
        }, () => {
            this.submitted = false;
        });
    }
}
