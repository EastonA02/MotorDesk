import express from "express";
import { protect } from "../middleware/authMiddleware.js";
import { createCustomer, getCustomers } from "../controllers/customerController.js";

const router = express.Router();

router.route("/")
  .post(protect, createCustomer)
  .get(protect, getCustomers);

export default router;
