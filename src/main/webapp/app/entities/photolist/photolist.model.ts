import { BaseEntity } from './../../shared';

export class Photolist implements BaseEntity {
    constructor(
        public id?: number,
        public uid?: string,
        public aid?: string,
        public title?: string,
        public author?: string,
        public story?: string,
        public picurl?: string,
        public sysType?: string,
        public ownType?: string,
        public isLoginCheck?: boolean,
        public isRecomment?: boolean,
        public uploadTime?: any,
        public orderindex?: number,
    ) {
        this.isLoginCheck = false;
        this.isRecomment = false;
    }
}
