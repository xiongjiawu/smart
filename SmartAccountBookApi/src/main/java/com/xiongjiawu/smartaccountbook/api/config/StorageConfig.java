package com.xiongjiawu.smartaccountbook.api.config;

import com.zhangzlyuyx.easy.storage.StorageEngine;
import com.zhangzlyuyx.easy.storage.StorageFactory;
import com.zhangzlyuyx.easy.storage.StorageType;
import com.zhangzlyuyx.easy.storage.gofastdfs.GoFastDFSStorageEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class StorageConfig {

	@Value("${storage.gofastdfs.server}")
	public String gofastdfsServer;

	@Value("${storage.gofastdfs.group}")
	private String gofastdfsGroup;

	@Value("${storage.gofastdfs.scene}")
	private String gofastdfsScene;

	@Value("${storage.gofastdfs.output}")
	private String gofastdfsOutput;

	private StorageEngine storageEngine;

	public StorageEngine getStorageEngine() {
		return this.storageEngine;
	}

	@PostConstruct
	public void init() {
		Map<String, Object> config = new HashMap<>();
		config.put(GoFastDFSStorageEngine.CONFIG_SERVER, this.gofastdfsServer);
		config.put(GoFastDFSStorageEngine.CONFIG_GROUP, this.gofastdfsGroup);
		config.put(GoFastDFSStorageEngine.CONFIG_SCENE, this.gofastdfsScene);
		config.put(GoFastDFSStorageEngine.CONFIG_OUTPUT, this.gofastdfsOutput);
		this.storageEngine = StorageFactory.getInstance().getStorageEngine(StorageType.GoFastDFS, config);
		this.storageEngine.loadConfig(config);
	}
}
