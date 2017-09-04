/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { FjepaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MemuserDetailComponent } from '../../../../../../main/webapp/app/entities/memuser/memuser-detail.component';
import { MemuserService } from '../../../../../../main/webapp/app/entities/memuser/memuser.service';
import { Memuser } from '../../../../../../main/webapp/app/entities/memuser/memuser.model';

describe('Component Tests', () => {

    describe('Memuser Management Detail Component', () => {
        let comp: MemuserDetailComponent;
        let fixture: ComponentFixture<MemuserDetailComponent>;
        let service: MemuserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FjepaTestModule],
                declarations: [MemuserDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MemuserService,
                    JhiEventManager
                ]
            }).overrideTemplate(MemuserDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MemuserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MemuserService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Memuser(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.memuser).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
