import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Memuser } from './memuser.model';
import { MemuserPopupService } from './memuser-popup.service';
import { MemuserService } from './memuser.service';

@Component({
    selector: 'jhi-memuser-delete-dialog',
    templateUrl: './memuser-delete-dialog.component.html'
})
export class MemuserDeleteDialogComponent {

    memuser: Memuser;

    constructor(
        private memuserService: MemuserService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.memuserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'memuserListModification',
                content: 'Deleted an memuser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-memuser-delete-popup',
    template: ''
})
export class MemuserDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private memuserPopupService: MemuserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.memuserPopupService
                .open(MemuserDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
