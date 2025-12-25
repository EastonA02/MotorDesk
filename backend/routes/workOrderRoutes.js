import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { authorize } from "../middleware/authorizeMiddleware.js";

import { createWorkOrder, getWorkOrders, updateWorkOrder } from "../controllers/workOrderController.js";

const router = express.Router();

router.route("/")
  .post(protect, authorize("Admin", "Owner"), createWorkOrder)
  .get(protect, getWorkOrders);

router.route("/:id")
  .put(protect, authorize("Admin", "Owner"), updateWorkOrder);

export default router;
