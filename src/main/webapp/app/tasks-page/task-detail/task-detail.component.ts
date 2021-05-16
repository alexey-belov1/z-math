import {EventEmitter, Component, OnInit, Output, Input} from '@angular/core';
import {ITask} from "../../shared/interfaces";
import {AttachedFileService} from "../../shared/services/attached-file.service";

@Component({
    selector: 'app-task-detail',
    templateUrl: './task-detail.component.html',
    styleUrls: ['./task-detail.component.scss']
})
export class TaskDetailComponent implements OnInit {

    @Input() task: ITask;
    @Output() close = new EventEmitter<void>()

    constructor(private attachedFileService: AttachedFileService,) {
    }

    ngOnInit(): void {
    }

    download(attachedFileId: number, attachedFileName: string): void {
        this.attachedFileService.download(attachedFileId).subscribe(res => {
            const url = window.URL.createObjectURL(res.body);
            const a = document.createElement('a');
            document.body.appendChild(a);
            a.setAttribute('style', 'display: none');
            a.href = url;
            a.download = attachedFileName;
            a.click();
            window.URL.revokeObjectURL(url);
            a.remove();
        });
    }
}
