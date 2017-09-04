import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Memuser } from './memuser.model';
import { MemuserService } from './memuser.service';

@Injectable()
export class MemuserPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private memuserService: MemuserService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.memuserService.find(id).subscribe((memuser) => {
                    memuser.createtime = this.datePipe
                        .transform(memuser.createtime, 'yyyy-MM-ddTHH:mm:ss');
                    memuser.edittime = this.datePipe
                        .transform(memuser.edittime, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.memuserModalRef(component, memuser);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.memuserModalRef(component, new Memuser());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    memuserModalRef(component: Component, memuser: Memuser): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.memuser = memuser;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
