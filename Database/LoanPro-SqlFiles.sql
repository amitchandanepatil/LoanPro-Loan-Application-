create database loanpro;
use loanpro;
CREATE TABLE borrower (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255),
    updated_date date
);


CREATE TABLE loan_application (
    id INT PRIMARY KEY AUTO_INCREMENT,
    borrower_id INT,
    loan_type VARCHAR(255) NOT NULL,
    application_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (borrower_id) REFERENCES Borrower(id)
);

CREATE TABLE loan_document (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_application_id INT,
    document_type VARCHAR(255) NOT NULL,
    document_path VARCHAR(255) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id)
);

CREATE TABLE loan_approval (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_application_id INT,
    approval_date DATE NOT NULL,
    approved_amount DECIMAL(10, 2) NOT NULL,
    interest_rate DECIMAL(5, 2) NOT NULL,
    repayment_terms VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id)
);

CREATE TABLE loan_disbursement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_application_id INT,
    disbursement_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id)
);

CREATE TABLE repayment_schedule (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_application_id INT,
    payment_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id)
);

CREATE TABLE loan_account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    borrower_id INT,
    loan_application_id INT,
    loan_amount DECIMAL(10, 2) NOT NULL,
    interest_rate DECIMAL(5, 2) NOT NULL,
    repayment_terms VARCHAR(255) NOT NULL,
    repayment_schedule_id INT,
    status VARCHAR(50) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (borrower_id) REFERENCES borrower(id),
    FOREIGN KEY (loan_application_id) REFERENCES loan_application(id),
    FOREIGN KEY (repayment_schedule_id) REFERENCES repayment_schedule(id)
);

CREATE TABLE customer_support (
    id INT PRIMARY KEY AUTO_INCREMENT,
    loan_account_id INT,
    issue TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_by varchar(255) NOT NULL,
    created_date date NOT NULL,
	updated_by varchar(255) ,
    updated_date date,
    FOREIGN KEY (loan_account_id) REFERENCES loan_account(id)
);


INSERT INTO borrower (username, password, name, address, contact_number, email, created_by, created_date)
VALUES
    ('user1', 'pass1', 'John Doe', '123 Main St', '123-456-7890', 'john.doe@example.com', 'admin', '2024-04-26'),
    ('user2', 'pass2', 'Jane Smith', '456 Elm St', '987-654-3210', 'jane.smith@example.com', 'admin', '2024-04-26'),
    ('user3', 'pass3', 'Alice Johnson', '789 Oak St', '555-555-5555', 'alice.johnson@example.com', 'admin', '2024-04-26'),
    ('user4', 'pass4', 'Bob Brown', '101 Pine St', '111-111-1111', 'bob.brown@example.com', 'admin', '2024-04-26'),
    ('user5', 'pass5', 'Eve White', '202 Cedar St', '222-222-2222', 'eve.white@example.com', 'admin', '2024-04-26'),
    ('user6', 'pass6', 'Charlie Black', '303 Maple St', '333-333-3333', 'charlie.black@example.com', 'admin', '2024-04-26'),
    ('user7', 'pass7', 'Grace Green', '404 Walnut St', '444-444-4444', 'grace.green@example.com', 'admin', '2024-04-26'),
    ('user8', 'pass8', 'David Grey', '505 Birch St', '555-555-5555', 'david.grey@example.com', 'admin', '2024-04-26'),
    ('user9', 'pass9', 'Frank Brown', '606 Pine St', '666-666-6666', 'frank.brown@example.com', 'admin', '2024-04-26'),
    ('user10', 'pass10', 'Helen White', '707 Oak St', '777-777-7777', 'helen.white@example.com', 'admin', '2024-04-26');


INSERT INTO loan_application (borrower_id, loan_type, application_date, status, created_by, created_date)
VALUES
    (1, 'Home Loan', '2024-04-26', 'Pending', 'admin', '2024-04-26'),
    (2, 'Car Loan', '2024-04-26', 'Approved', 'admin', '2024-04-26'),
    (3, 'Personal Loan', '2024-04-26', 'Pending', 'admin', '2024-04-26'),
    (4, 'Business Loan', '2024-04-26', 'Approved', 'admin', '2024-04-26'),
    (5, 'Equipment Loan', '2024-04-26', 'Pending', 'admin', '2024-04-26'),
    (6, 'Investment Loan', '2024-04-26', 'Approved', 'admin', '2024-04-26'),
    (7, 'Institutional Loan', '2024-04-26', 'Pending', 'admin', '2024-04-26'),
    (8, 'Project Financing', '2024-04-26', 'Approved', 'admin', '2024-04-26'),
    (9, 'Peer-to-Peer Lending', '2024-04-26', 'Pending', 'admin', '2024-04-26'),
    (10, 'Jewel Loan', '2024-04-26', 'Approved', 'admin', '2024-04-26');
    
    INSERT INTO loan_document (loan_application_id, document_type, document_path, created_by, created_date)
VALUES
    (1, 'Proof of Income', '/documents/proof_of_income.pdf', 'admin', '2024-04-26'),
    (2, 'Vehicle Registration', '/documents/vehicle_registration.pdf', 'admin', '2024-04-26'),
    (3, 'Pay Slip', '/documents/pay_slip.pdf', 'admin', '2024-04-26'),
    (4, 'Business Plan', '/documents/business_plan.pdf', 'admin', '2024-04-26'),
    (5, 'Equipment List', '/documents/equipment_list.pdf', 'admin', '2024-04-26'),
    (6, 'Investment Portfolio', '/documents/investment_portfolio.pdf', 'admin', '2024-04-26'),
    (7, 'Institution Registration', '/documents/institution_registration.pdf', 'admin', '2024-04-26'),
    (8, 'Project Proposal', '/documents/project_proposal.pdf', 'admin', '2024-04-26'),
    (9, 'Peer Agreement', '/documents/peer_agreement.pdf', 'admin', '2024-04-26'),
    (10, 'Jewel Appraisal', '/documents/jewel_appraisal.pdf', 'admin', '2024-04-26');
    
    INSERT INTO loan_approval (loan_application_id, approval_date, approved_amount, interest_rate, repayment_terms, status, created_by, created_date)
VALUES
    (1, '2024-04-26', 250000.00, 5.0, '5 years', 'Approved', 'admin', '2024-04-26'),
    (2, '2024-04-26', 35000.00, 4.5, '3 years', 'Approved', 'admin', '2024-04-26'),
    (3, '2024-04-26', 15000.00, 6.0, '2 years', 'Approved', 'admin', '2024-04-26'),
    (4, '2024-04-26', 500000.00, 7.0, '7 years', 'Approved', 'admin', '2024-04-26'),
    (5, '2024-04-26', 100000.00, 6.5, '4 years', 'Approved', 'admin', '2024-04-26'),
    (6, '2024-04-26', 200000.00, 8.0, '6 years', 'Approved', 'admin', '2024-04-26'),
    (7, '2024-04-26', 750000.00, 7.5, '8 years', 'Approved', 'admin', '2024-04-26'),
    (8, '2024-04-26', 1000000.00, 6.8, '5 years', 'Approved', 'admin', '2024-04-26'),
    (9, '2024-04-26', 50000.00, 5.5, '3 years', 'Approved', 'admin', '2024-04-26'),
    (10, '2024-04-26', 20000.00, 6.2, '2 years', 'Approved', 'admin', '2024-04-26');
    
    INSERT INTO loan_disbursement (loan_application_id, disbursement_date, amount, status, created_by, created_date)
VALUES
    (1, '2024-04-27', 250000.00, 'Disbursed', 'admin', '2024-04-27'),
    (2, '2024-04-27', 35000.00, 'Disbursed', 'admin', '2024-04-27'),
    (3, '2024-04-27', 15000.00, 'Disbursed', 'admin', '2024-04-27'),
    (4, '2024-04-27', 500000.00, 'Disbursed', 'admin', '2024-04-27'),
    (5, '2024-04-27', 100000.00, 'Disbursed', 'admin', '2024-04-27'),
    (6, '2024-04-27', 200000.00, 'Disbursed', 'admin', '2024-04-27'),
    (7, '2024-04-27', 750000.00, 'Disbursed', 'admin', '2024-04-27'),
    (8, '2024-04-27', 1000000.00, 'Disbursed', 'admin', '2024-04-27'),
    (9, '2024-04-27', 50000.00, 'Disbursed', 'admin', '2024-04-27'),
    (10, '2024-04-27', 20000.00, 'Disbursed', 'admin', '2024-04-27');

INSERT INTO repayment_schedule (loan_application_id, payment_date, amount, status, created_by, created_date)
VALUES
    (1, '2024-05-01', 5000.00, 'Pending', 'admin', '2024-04-26'),
    (2, '2024-05-01', 1000.00, 'Pending', 'admin', '2024-04-26'),
    (3, '2024-05-01', 750.00, 'Pending', 'admin', '2024-04-26'),
    (4, '2024-05-01', 8000.00, 'Pending', 'admin', '2024-04-26'),
    (5, '2024-05-01', 5000.00, 'Pending', 'admin', '2024-04-26'),
    (6, '2024-05-01', 10000.00, 'Pending', 'admin', '2024-04-26'),
    (7, '2024-05-01', 15000.00, 'Pending', 'admin', '2024-04-26'),
    (8, '2024-05-01', 20000.00, 'Pending', 'admin', '2024-04-26'),
    (9, '2024-05-01', 2500.00, 'Pending', 'admin', '2024-04-26'),
    (10, '2024-05-01', 3000.00, 'Pending', 'admin', '2024-04-26');
    
    INSERT INTO loan_account (borrower_id, loan_application_id, loan_amount, interest_rate, repayment_terms, repayment_schedule_id, status, created_by, created_date)
VALUES
    (1, 1, 250000.00, 5.0, '5 years', 1, 'Active', 'admin', '2024-04-26'),
    (2, 2, 35000.00, 4.5, '3 years', 2, 'Active', 'admin', '2024-04-26'),
    (3, 3, 15000.00, 6.0, '2 years', 3, 'Active', 'admin', '2024-04-26'),
    (4, 4, 500000.00, 7.0, '7 years', 4, 'Active', 'admin', '2024-04-26'),
    (5, 5, 100000.00, 6.5, '4 years', 5, 'Active', 'admin', '2024-04-26'),
    (6, 6, 200000.00, 8.0, '6 years', 6, 'Active', 'admin', '2024-04-26'),
    (7, 7, 750000.00, 7.5, '8 years', 7, 'Active', 'admin', '2024-04-26'),
    (8, 8, 1000000.00, 6.8, '5 years', 8, 'Active', 'admin', '2024-04-26'),
    (9, 9, 50000.00, 5.5, '3 years', 9, 'Active', 'admin', '2024-04-26'),
    (10, 10, 20000.00, 6.2, '2 years', 10, 'Active', 'admin', '2024-04-26');
    
    INSERT INTO customer_support (loan_account_id, issue, status, created_by, created_date)
VALUES
    (1, 'Payment not reflecting', 'Open', 'admin', '2024-04-26'),
    (2, 'Change of repayment date', 'Open', 'admin', '2024-04-26'),
    (3, 'Account balance discrepancy', 'Open', 'admin', '2024-04-26'),
    (4, 'Loan restructuring request', 'Open', 'admin', '2024-04-26'),
    (5, 'Payment reminder request', 'Open', 'admin', '2024-04-26'),
    (6, 'Loan statement request', 'Open', 'admin', '2024-04-26'),
    (7, 'Interest rate query', 'Open', 'admin', '2024-04-26'),
    (8, 'Late payment fee dispute', 'Open', 'admin', '2024-04-26'),
    (9, 'Loan application status inquiry', 'Open', 'admin', '2024-04-26'),
    (10, 'Loan closure request', 'Open', 'admin', '2024-04-26');








