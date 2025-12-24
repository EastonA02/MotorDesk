import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { createCustomer, getCustomers } from "../controllers/customerController.js";
import { getCustomerVehicles } from "../controllers/customerController.js";
import { getCustomerWorkOrders } from "../controllers/customerController.js";

const router = express.Router();

router.route("/")
  .post(protect, createCustomer)
  .get(protect, getCustomers);

router.route("/:id/vehicles") //get all vehicles belonging to customer
  .get(protect, getCustomerVehicles);

router.route("/:id/workOrders") //get all work orders belonging to customer
  .get(protect, getCustomerWorkOrders);

export default router;
