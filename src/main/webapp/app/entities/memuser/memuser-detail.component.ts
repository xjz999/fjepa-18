import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Memuser } from './memuser.model';
import { MemuserService } from './memuser.service';

@Component({
    selector: 'jhi-memuser-detail',
    templateUrl: './memuser-detail.component.html'
})
export class MemuserDetailComponent implements OnInit, OnDestroy {

    memuser: Memuser;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private memuserService: MemuserService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMemusers();
    }

    load(id) {
        this.memuserService.find(id).subscribe((memuser) => {
            this.memuser = memuser;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMemusers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'memuserListModification',
            (response) => this.load(this.memuser.id)
        );
    }
}
