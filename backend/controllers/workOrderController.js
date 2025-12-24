import WorkOrder from "../models/workOrderModel.js";

//create new work order
export const createWorkOrder = async (req, res) => {
  const { customer, vehicle, description, status } = req.body;

  if (!customer || !vehicle || !description || !status) {
    return res.status(400).json({ message: "customer, vehicle, description are required" });
  }

  const workOrder = await WorkOrder.create({
    customer,
    vehicle,
    description,
    status,
    createdBy: req.user._id, //requires protect middleware
  });

  res.status(201).json(workOrder);
};

//get all work orders
export const getWorkOrders = async (req, res) => {
  const workOrders = await WorkOrder.find()
    .populate("customer", "name phone email")
    .populate("vehicle", "make model year licensePlate vin")
    .populate("status")
    .populate("createdBy", "name role")
    .sort({ createdAt: -1 });

  res.json(workOrders);
};

//update work order
export const updateWorkOrder = async(req,res) => {
  const workOrderId = req.params.id;

  const updatedWorkOrder = await WorkOrder.findByIdAndUpdate(
    workOrderId, //id of work order to update
    req.body, //fields to update
    {
      new: true, //return new document
      runValidators: true, //ensure enums and other rules aren't validated
    }
  );

  //Work order not found
  if (!updatedWorkOrder) {
    return res.status(400).json( { message: "Work order not found" });
  }

  res.json(updatedWorkOrder)
};
