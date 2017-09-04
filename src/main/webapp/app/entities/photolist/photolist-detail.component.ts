import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Photolist } from './photolist.model';
import { PhotolistService } from './photolist.service';

@Component({
    selector: 'jhi-photolist-detail',
    templateUrl: './photolist-detail.component.html'
})
export class PhotolistDetailComponent implements OnInit, OnDestroy {

    photolist: Photolist;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private photolistService: PhotolistService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPhotolists();
    }

    load(id) {
        this.photolistService.find(id).subscribe((photolist) => {
            this.photolist = photolist;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPhotolists() {
        this.eventSubscriber = this.eventManager.subscribe(
            'photolistListModification',
            (response) => this.load(this.photolist.id)
        );
    }
}
