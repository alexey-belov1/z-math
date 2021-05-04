import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IReview} from '../../shared/interfaces';
import {ReviewService} from '../../shared/services/review.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-review',
  templateUrl: './new-review.component.html',
  styleUrls: ['./new-review.component.scss']
})
export class NewReviewComponent implements OnInit {

  @Output() onAdd = new EventEmitter();

  form: FormGroup;
  submitted = false;

  quillConfiguration = {
    toolbar: [
      ['bold', 'italic', 'underline', 'strike'],
      ['blockquote'],
      [{ list: 'ordered' }, { list: 'bullet' }],
      [{ header: '1' }, { header: '2' }],
      [{ header: [1, 2, 3, 4, 5, 6, false] }],
      [{ color: [] }, { background: [] }],
      ['clean'],
      ['link'],
    ],
  };

  constructor(
    private reviewSevice: ReviewService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      text: new FormControl(null, [
        Validators.required
      ])
    });
  }

  submit(): void {
    if (this.form.invalid) {
      return;
    }

    this.submitted = true;

    const review: IReview = {
      user: {
        id: 1
      },
      text: this.form.value.text
    };

    this.reviewSevice.save(review).subscribe(() => {
      this.form.reset();
      this.onAdd.emit();
    }, () => {
      this.submitted = false;
    });
  }
}
