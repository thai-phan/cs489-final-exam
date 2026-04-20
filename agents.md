Royal Windsor Realty, LLC are a national Real Estate Management company who own and
manage residential apartment homes in properties located in cities across the country.
Assume that the company has hired you, as a Software Developer, to design and develop
application software for their Apartments Leasing Management system, which they will be
using to manage data about their Apartments, the Leases and the Tenants who are the
leaseholders. They want you to design and implement a Web API backend solution for this
system.
An important need for the company managers is to be able to view the list of all Apartments
that they own and manage. And also, to see the Revenue (income) that accrues from the
Leases.
An Apartment is a residential accommodation that can be rented-out through the issuance
and signing of a Lease agreement.
A Lease is an agreement or contract issued to a rental customer, known as a "Tenant",
which allows the tenant to occupy, live and reside in a specific Apartment for a given period
(counted in calendar months). 

An Apartment is located at an Address. And one Address can only be associated to one
Apartment.
An Apartment will have many Leases.
A Lease can be associated with only one Apartment.
A Tenant will have one or more Leases.
A Lease can only be issued to just one Tenant, as the leaseholder. And every Lease must be
associated with a Tenant.
Note: It is possible that some Apartment(s) may NOT have had any Lease, for example, if it
is in a newly constructed property and so has not yet been rented out. However, every
Lease must be associated with an Apartment. The system MUST NOT have a Lease without
an Apartment associated. In other words, there CANNOT be a Lease for an unknown
Apartment.
Also, every Lease MUST be associated to a Tenant, who is the leaseholder. There SHOULD
NOT be a Lease for an unknown Tenant. Likewise, every Tenant MUST have at least 1 lease,
that they have signed in order to become a tenant. 


Apartment:
apartmentId: Integer, (Primary Key field)
apartmentNumber, (required field)
propertyName, (required field)
floorNo (optional field)
size (in square feet)
numberOfRooms

Here are the attributes for the Address, Tenant and Lease entities, including some useful
descriptions and/or sample data values:

Address:
apartmentNumber,
street,
city
state
zipCode
Tenant:
firstName (required field)
lastName, (required field)
phoneNumber, (required field)
Email (optional field)
Lease:
leaseId: (Primary Key field)
leaseNumber: (Required field, Unique)
startDate: (required)
endDate: (required)
monthlyRentalRate: (required) e.g. $1,750, $2,500 etc

