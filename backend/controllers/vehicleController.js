import Vehicle from "../models/vehicleModel.js";

export const createVehicle = async (req, res) => {
  const { customer, make, model, year, licensePlate, vin } = req.body;

  if (!customer || !make || !model || !year || !licensePlate) {
    return res.status(400).json({ message: "Missing required fields" });
  }

  const vehicle = await Vehicle.create({ customer, make, model, year, licensePlate, vin });
  res.status(201).json(vehicle);
};

export const getVehicles = async (req, res) => {
  const vehicles = await Vehicle.find()
    .populate("customer", "name phone email") //fill cusomter fields 
    .sort({ createdAt: -1 }); 

  res.json(vehicles);
};

