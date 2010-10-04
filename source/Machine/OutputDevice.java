package Machine;

/*-----------------------------------------------------------------------------
	Устройство вывода.
-----------------------------------------------------------------------------*/
public class OutputDevice implements InternalDevice
{
	public OutputDevice(ERegisterFactory reg_factory)
	{
		data_reg = new ERegister(8);
		state_flag = new EFlag();
		data_channel = new EIOChannel(data_reg, reg_factory.Accumulator());
		
		order_channel =			new EChannel(null, null);
		state_flag_channel =	new EChannel(null, null);
		adress_channel =		new EChannel(null, null);
		intrpt_channel =		new EChannel(null, null);
	}

	public EFlag getStateFlag()
	{
		return state_flag;
	}

	public ERegister getDataRegister()
	{
		return data_reg;
	}

	public IChannel getDataChannel()
	{
		return data_channel;
	}
	public IChannel	getIORequestChannel()
	{
		return order_channel;
	}
	
	public IChannel	getAdressChannel()
	{
		return adress_channel;
	}
	
	public IChannel	getStateFlagChannel()
	{
		return state_flag_channel;
	}
	
	public IChannel	getInterruptionRequestChannel()
	{
		return intrpt_channel;
	}
	
	private EChannel	order_channel;		// Приказ на ввод/вывод
	private EChannel	adress_channel;		// Адрес ВУ
	private EChannel	state_flag_channel;	// Состояние флагов ВУ
	private EChannel	intrpt_channel;		// Запрос прерывания
	
	private EIOChannel	data_channel;		// Шина ввода/вывода
	private ERegister	data_reg;			// Регистра данных
	private EFlag		state_flag;			// флаг ВУ
}