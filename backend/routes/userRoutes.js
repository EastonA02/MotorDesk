import express from "express"
import { authorize } from "../middleware/authorizeMiddleware.js";
import { registerUser, loginUser } from "../controllers/userController.js"


const router = express.Router();

router.post("/register", registerUser);
router.post("/login", loginUser);

export default router;