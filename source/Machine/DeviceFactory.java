package Machine;

/*-----------------------------------------------------------------------------
	Фабрика устройств ввода/вывода
-----------------------------------------------------------------------------*/
public class DeviceFactory
{
	public DeviceFactory(ERegisterFactory reg_factory)
	{
		dev = new InternalDevice[3];
		dev[0] = new OutputDevice(reg_factory);
		dev[1] = new InputDevice(reg_factory);
		dev[2] = new InputDevice(reg_factory);
	}
	
	public InternalDevice getOutputDevice()
	{
		return dev[0];
	}
	
	public InternalDevice getInputDevice1()
	{
		return dev[1];
	}
	
	public InternalDevice getInputDevice2()
	{
		return dev[2];
	}
	
	public InternalDevice getDeviceByAdress(int dev_adr)
	{
		dev_adr = dev_adr - 1;
		if ((dev_adr >= 0) && (dev_adr <= 2))
		{
			return dev[dev_adr];
		}
		else
		{
			return null;
		}
	}
	
	public void clearAllFlags()
	{
		for(InternalDevice x : dev)
		{
			x.getStateFlag().ClearFlag();
		}
	}
	
	public void closeAllChannels()
	{
		for (InternalDevice x : dev)
		{
				x.getDataChannel().Close();
				
				x.getAdressChannel().Close();
				x.getInterruptionRequestChannel().Close();
				x.getIORequestChannel().Close();
				x.getStateFlagChannel().Close();
		}
	}
	
	private InternalDevice dev[];
}