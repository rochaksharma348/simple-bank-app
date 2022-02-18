export class Transfer {
    senderId : number;
    receiverId : number;
    amount : number;

    constructor(sid : number, rid : number, amount : number) {
        this.senderId = sid;
        this.receiverId = rid;
        this.amount = amount;
    }
}
