import WorkOrder from "../models/workOrderModel.js";

export const createWorkOrder = async (req, res) => {
  const { customer, vehicle, description } = req.body;

  if (!customer || !vehicle || !description) {
    return res.status(400).json({ message: "customer, vehicle, description are required" });
  }

  const workOrder = await WorkOrder.create({
    customer,
    vehicle,
    description,
    createdBy: req.user._id, //requires protect middleware
  });

  res.status(201).json(workOrder);
};

export const getWorkOrders = async (req, res) => {
  const workOrders = await WorkOrder.find()
    .populate("customer", "name phone email")
    .populate("vehicle", "make model year licensePlate vin")
    .populate("createdBy", "name role")
    .sort({ createdAt: -1 });

  res.json(workOrders);
};
