interface MessageResponse{
    code:number;
    message:string;
}

export interface User {
  id: number;
  username: string;
  fullName: string;
  email: string;
}

export interface Country {
  countryId: number;
  countryName: string;
  returnMessage: MessageResponse;
}

export interface State {
  stateId: number;
  stateName: string;
  country: Country;
  returnMessage: MessageResponse;
}

export interface City {
  cityId: number;
  cityName: string;
  state: State;
  returnMessage: MessageResponse;
}

export type ClientType =
  | "INDIVIDUAL"
  | "COMPANY";

export interface Client {
  clientId: number;
  clientType: ClientType;
  companyName: string;
  contactPersonName: string;
  email: string;
  mobileNo: string;
  whatsappNo: string;
  website: string;
  address: string;
  city: City;
  postalCode: string;
  remarks: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface LeadStatus {
  leadStatusId: number;
  statusName: string;
  returnMessage: MessageResponse;
}

export interface Product {
  productId: number;
  productName: string;
}

export type PriorityLevel =
  | "LOW"
  | "MEDIUM"
  | "HIGH";

export interface Lead {
  leadId: number;
  client: Client;
  interestedProduct: Product;
  leadStatus: LeadStatus;
  leadSource: string;
  expectedBudget: number;
  expectedClosureDate: string;
  priorityLevel: PriorityLevel;
  remarks: string;
  assignedTo: User;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export type CommunicationMode =
  | "CALL"
  | "EMAIL"
  | "WHATSAPP"
  | "MEETING"
  | "VIDEO_CALL";

export interface LeadFollowup {
  followupId: number;
  lead: Lead;
  followupDate: string;
  nextFollowupDate: string;
  communicationMode: CommunicationMode;
  discussionSummary: string;
  followupStatus: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface LeadStatus {
  leadStatusId: number;
  statusName: string;
  returnMessage: MessageResponse;
}

export interface Payment {
  paymentId: number;
  salesInvoice: SalesInvoice;
  paymentDate: string;
  paymentAmount: number;
  paymentMethod: string;
  transactionReference: string;
  currencyCode: string;
  paymentNotes: string;
  receivedBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface Product {
  productId: number;
  productName: string;
  productCode: string;
  productType: string;
  description: string;
  monthlyPrice: number;
  yearlyPrice: number;
  oneTimePrice: number;
  isActive: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface ProductDemo {
  demoId: number;
  lead: Lead;
  demoDatetime: string;
  demoStatus: string;
  meetingLink: string;
  demoFeedback: string;
  conductedBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface SalesInvoice {
  salesInvoiceId: number;
  invoiceNo: string;
  invoiceDate: string;
  client: Client;
  lead: Lead;
  subtotalAmount: number;
  discountAmount: number;
  taxAmount: number;
  totalAmount: number;
  currencyCode: string;
  paymentStatus: string;
  invoiceStatus: string;
  dueDate: string;
  notes: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface SalesInvoiceDetail {
  salesInvoiceDetailId: number;
  salesInvoice: SalesInvoice;
  product: Product;
  itemDescription: string;
  quantity: number;
  unitPrice: number;
  discountPercent: number;
  taxPercent: number;
  lineTotal: number;
  returnMessage: MessageResponse;
}

export interface Task {
  taskId: number;
  taskTitle: string;
  taskDescription: string;
  relatedLead: Lead;
  assignedTo: User;
  taskStatus: string;
  priority: string;
  dueDate: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface TrainingCourse {
  courseId: number;
  courseName: string;
  technologyArea: string;
  description: string;
  durationInDays: number;
  fees: number;
  isActive: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface TrainingEnrollment {
  enrollmentId: number;
  client: Client;
  course: TrainingCourse;
  enrollmentDate: string;
  batchName: string;
  feesAmount: number;
  paymentStatus: string;
  trainingStatus: string;
  remarks: string;
  createdBy: User;
  createdAt: string;
  returnMessage: MessageResponse;
}

export interface Month {
  monthId: number; 
  monthName: string; 
}