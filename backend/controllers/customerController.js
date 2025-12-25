import Customer from "../models/customerModel.js"
import Vehicle from "../models/vehicleModel.js";
import WorkOrder from "../models/workOrderModel.js";

import { validateObjectId } from "../utils/validateObjectId.js";

//create customer
export const createCustomer = async (req, res) => {
  const { name, phone, email } = req.body;

  if (!name || !phone || !email) {
    //return res.status(400).json({message: "name, phone, email are required"});
    res.status(400);
    throw new Error("Name, phone, and email are required");
  }

  const customer = await Customer.create({
     name,
     phone,
     email,
     createdBy: req.user._id, //show user who created new customer; req.user comes from protect middleware
    });
  res.status(201).json(customer); //201="created"
};

export const getCustomers = async (req, res) => {
  const customers = await Customer.find().sort({ createdAt: -1 }); //ordered so newewst appear first
  res.json(customers);
};

export const getCustomerVehicles = async (req,res) => {
  const customerId = req.params.id; //req.params.id comes from URL

  validateObjectId(customerId, "customer ID");

  const customer = await Customer.findById(customerId);

  //handle error for bad ID -> Non existent customer -> 404 (not found)
  if (!customer) {
    res.status(404);
    throw new Error("Customer not found");
  }

  const vehicles = await Vehicle.find({ customer: customerId }) //get all vehicles where customer = this id
    .sort({ createdAt: -1 });

  res.json(vehicles);
};

export const getCustomerWorkOrders = async (req,res) => {
  const customerId = req.params.id;

  validateObjectId(customerId, "customer ID");

  const customer = await Customer.findById(customerId);

  //handle error for bad ID -> Non existent customer -> 404 (not found)
  if (!customer) {
    res.status(404);
    throw new Error("Customer not found");
  }

  const workOrders = await WorkOrder.find({ customer: customerId }) //get all work orders where customer = this id
    .sort({ createdAt: -1 });
  
  res.json(workOrders);
}