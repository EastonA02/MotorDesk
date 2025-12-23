import Customer from "../models/customerModel.js"

//create customer
export const createCustomer = async (req, res) => {
  const { name, phone, email } = req.body;

  if (!name || !phone || !email) {
    return res.status(400).json({message: "name, phone, email are required"});
  }

  const customer = await Customer.create({
     name,
     phone,
     email,
     createdBy: req.user._id, //show user who created new customer; req.user comes from protect middleware
    });
  res.status(200).json(customer);
};

export const getCustomers = async (req, res) => {
  const customers = await Customer.find().sort({ createdAt: -1 }); //ordered so newewst appear first
  res.json(customers);
};