import {Directive, HostListener, Input} from '@angular/core';

@Directive({
    selector: '[appTooltip]'
})
export class TooltipDirective {

    @Input('appTooltip') ref: string;

    constructor(){ }

    @HostListener('mouseenter') onEnter() {
        document.getElementById(this.ref).classList.add('visibility');
    }

    @HostListener('mouseleave') onLeave() {
        document.getElementById(this.ref).classList.remove('visibility');
    }
}
