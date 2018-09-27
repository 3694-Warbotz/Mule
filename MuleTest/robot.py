import wpilib

class myRobot(wpilib.IterativeRobot):

    #used for definitions and assignments
    def robotInit(self):
        self.leftMotor = wpilib.Victor(0)
        self.rightMotor = wpilib.Victor(1)
        self.stick = wpilib.Joystick(0)
        self.Timer = wpilib.Timer()

    #called just before the autonomous period
    def autonomousInit(self):
        self.Timer.reset()
        self.Timer.start()

    #called repeatedly during autonomous
    def autonomousPeriodic(self):
        if self.Timer < 2:
            self.leftMotor.set(0.6)
            self.rightMotor.set(0.6)
        else:
            self.leftMotor.set(0.0)
            self.rightMotor.set(0.0)

    #called repeatedly during teleop
    def teleopPeriodic(self):
        turn = self.stick.getX()
        throttle = self.stick.getY()
        self.leftMotor.set(throttle + turn)
        self.rightMotor.set(throttle - turn)
